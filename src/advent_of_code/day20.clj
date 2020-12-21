(ns advent-of-code.day20
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.math.combinatorics :as comb]))

(defn- get-input [func input]
  (-> input
      (str/split #"\n\n")
      func))

(defn- parse-tile [block]
  (let [[header & lines] (str/split-lines block)
        id               (Integer/parseInt (first (re-seq #"\d+" header)))]
    (list id (vec lines))))

(defn- parse-data [blocks]
  (reduce (fn [data block]
            (apply assoc data (parse-tile block)))
          {} blocks))

(defn- to-int [v]
  (Integer/parseInt (str/join (map {\# \1, \. \0} v)) 2))

(defn- calc-edges [tile]
  (let [N  (to-int (first tile))
        N' (to-int (reverse (first tile)))
        S  (to-int (last tile))
        S' (to-int (reverse (last tile)))
        W  (to-int (mapv first tile))
        W' (to-int (reverse (mapv first tile)))
        E  (to-int (mapv last tile))
        E' (to-int (reverse (mapv last tile)))]
    [N E S W N' E' S' W']))

(defn- reduce-edges [edgelist]
  (set (apply map min (partition 4 edgelist))))

(defn- create-edgelists [tilemap]
  (reduce (fn [edgesets id]
            (assoc edgesets id (calc-edges (tilemap id))))
          {} (keys tilemap)))

(defn- create-edgesets [edgelists]
  (reduce (fn [edges id]
            (assoc edges id (reduce-edges (edgelists id))))
          {} (keys edgelists)))

(defn- all-edges [edgesets] (mapcat concat (vals edgesets)))

(defn- frame-edges [edgesets]
  (let [edges (frequencies (all-edges edgesets))]
    (set (filter #(= 1 (edges %)) (keys edges)))))

(defn- find-corners [edgesets]
  (let [cedges (frame-edges edgesets)]
    (filter #(= 2 (count (set/intersection cedges (edgesets %))))
            (keys edgesets))))

(defn part-1
  "Day 20 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       create-edgelists
       create-edgesets
       find-corners
       (reduce *)))

(defn- flip [tile]
  (mapv #(vec (reverse %)) tile))

(defn- rotate [times tile]
  (let [dim  (count tile)
        tile (mapv vec tile)]
    (mapv #(str/join %)
          (reduce (fn [tile _]
                    (mapv #(vec (reverse %))
                          (reduce (fn [t xy]
                                    (assoc-in (assoc-in t (reverse xy)
                                                        (get-in t xy))
                                              xy (get-in t (reverse xy))))
                                  tile (for [x (range dim), y (range dim)
                                             :when (< y x)]
                                         [x y]))))
                  tile (range times)))))

(defn- transform [tile which]
  (case which
    (0 1 2 3) (rotate which tile)
    (4 5 6 7) (flip (rotate (rem which 4) tile))))

(defn- remove-borders [tile]
  (let [lines (rest (butlast tile))]
    (mapv #(vec (rest (butlast %))) lines)))

(defn- neighbors [cur idx dim]
  (if (zero? (mod idx 3))
    (list (cur (- idx dim)))
    (map #(cur %) (filter #(<= 0 %) [(dec idx) (- idx dim)]))))

(defn- linkable? [must new edges]
  (let [newedges  (edges new)
        mustedges (map edges must)]
    (empty?
     (filter #(empty? (set/intersection % newedges)) (map edges must)))))

(defn- connection [idx dim current]
  (if (zero? (mod idx dim))
    {:match (current (- idx dim))
     :match-at :south
     :match-to :north}
    {:match (current (dec idx))
     :match-at :east
     :match-to :west}))

(defn- get-orient-val [orient side edges]
  (let [[N E S W N' E' S' W'] edges]
    (side (zipmap (list :north :east :south :west)
                  (case orient
                    0 [N E S W]
                    1 [W N E S]
                    2 [S W N E]
                    3 [E S W N]
                    4 [N' W S' E]
                    5 [W' S E' N]
                    6 [S' E N' W]
                    7 [E' N W' S])))))

(defn- find-orientation [tile edges side val]
  (let [side->orient (mapv #(get-orient-val % side edges) (range 8))]
    (prn tile side side->orient)
    (first (filter #(= (side->orient %) val) (range 8)))))

(defn- try-orientation [imap edges size dim orient]
  (prn "try-orientation" orient)
  (loop [sol [(list (imap 0) orient)], idx 1]
    (prn sol idx)
    (case idx
      size sol
      (let [tile        (imap idx)
            match-to    (connection idx dim sol)
            _ (prn match-to)
            edgeval     (get-orient-val (last (:match match-to))
                                        (:match-at match-to)
                                        (edges (first (:match match-to))))
            _ (prn edgeval)
            orientation (find-orientation tile (edges tile)
                                          (:match-to match-to) edgeval)
            _ (prn orientation)]
        (case orientation
          nil nil
          (recur (conj sol (list tile orientation)) (inc idx)))))))

(defn- attach-orientations [imagemap edgelists]
  (let [size (count imagemap)
        dim  (int (Math/sqrt size))]
    (first (filter #(vector? (try-orientation imagemap edgelists size dim %))
                   (range 8)))))

(defn- find-image-2 [sol choices alltiles edges size dim]
  (cond
    (= size (count sol)) sol
    (empty? choices)     nil
    :else
    (let [current    (last sol)
          must-match (neighbors sol (count sol) dim)
          _ (prn (count sol) must-match sol)
          candidates (filter #(linkable? must-match % edges) choices)
          _ (prn candidates)]
      (loop [[c & candidates] candidates]
        (cond
          (nil? c) nil
          :else
          (let [newsol (find-image-2 (conj sol c)
                                     (disj choices c)
                                     alltiles edges size dim)]
            (cond
              (not (nil? newsol)) newsol
              :else  (recur candidates))))))))

(defn- find-image [tilemap dim corner]
  (let [tileset   (set (keys tilemap))
        choices   (disj tileset corner)
        edgelists (create-edgelists tilemap)
        edgesets  (create-edgesets edgelists)]
    (attach-orientations (find-image-2 [corner] choices
                                       tileset edgesets (count tileset) dim)
                         edgelists)))

(defn- create-line [row line] (str/join (map #(% line) row)))

(defn- expand [grid]
  (let [x-range   (count (first grid))
        y-range   (count grid)
        lines-per (count (ffirst grid))]
    (vec (for [y (range y-range), line (range lines-per)]
           (str/join (map #(% line) (grid y)))))))

(defn- build-image [tilemap corner]
  (let [size     (count (keys tilemap))
        dim      (int (Math/sqrt size))
        image    (vec (repeat dim (vec (repeat dim nil))))
        imagemap (find-image tilemap dim corner)]
    (expand (reduce (fn [img idx]
                      (let [[id orient] (imagemap idx)]
                        (assoc-in img [(quot idx dim) (rem idx dim)]
                                  (transform (remove-borders (tilemap id))
                                             orient))))
                    image (range size)))))

(def monster ["..................#."
              "#....##....##....###"
              ".#..#..#..#..#..#..."])
(def monster-width (count (first monster)))
(def monster-height (count monster))
(def monster-tiles ((frequencies (str/join monster)) \#))

(defn- monster-at? [image y x]
  (let [hits (apply + (for [my (range monster-height), mx (range monster-width)]
                        (if (= \#
                               (get-in monster [my mx])
                               (get-in image [(+ my y) (+ mx x)]))
                          1 0)))]
    (= hits monster-tiles)))

(defn- count-monsters [image]
  (let [iwidth  (count (first image))
        iheight (count image)
        range-x (inc (- iwidth monster-width))
        range-y (inc (- iheight monster-height))]
    (apply + (for [y (range range-y), x (range range-x)]
               (if (monster-at? image y x) 1 0)))))

(defn- solve-part-2 [input]
  (let [parsed  (get-input parse-data input)
        corners (->> parsed
                     create-edgelists
                     create-edgesets
                     find-corners)
        image   (build-image parsed (first corners))
        totalsq ((frequencies (str/join image)) \#)]
    (->> (apply max (map #(count-monsters (transform image %)) (range 8)))
         (* 15)
         (- totalsq))))

(defn part-2
  "Day 20 Part 2"
  [input]
  (solve-part-2 input))
