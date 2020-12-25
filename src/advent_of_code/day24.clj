(ns advent-of-code.day24
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- parse-line [line]
  (loop [[ch & line] line, inst []]
    (case ch
      nil     inst
      (\e \w) (recur line (conj inst (str ch)))
      (\s \n) (recur (rest line) (conj inst (str ch (first line)))))))

(defn- parse-data [lines] (map parse-line lines))

(def deltas {"e"  [0 2]
             "se" [1 1]
             "sw" [1 -1]
             "w"  [0 -2]
             "nw" [-1 -1]
             "ne" [-1 1]})

(def neighbors (vals deltas))

(defn- move [from by]
  (reduce (fn [dest dir]
            (mapv + dest (deltas dir)))
          from by))

(defn- run-instructions [insts]
  (let [floor {[0 0] :white}
        flip  {:white :black, :black :white}]
    (reduce (fn [fl inst]
              (let [coord (move [0 0] inst)]
                (assoc fl coord (flip (get fl coord :white)))))
            floor insts)))

(defn- count-tiles [color floor]
  (count (color (group-by identity (vals floor)))))

(defn part-1
  "Day 24 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       run-instructions
       (count-tiles :black)))

(defn- surrounds [coord]
  (map #(mapv + coord %) neighbors))

(defn- step [floor]
  (let [cells (filter #(= (floor %) :black) (keys floor))]
    (into {}
          (for [[loc n] (frequencies (mapcat surrounds cells))]
            (let [color (get floor loc :white)]
              [loc (cond
                     (and (= color :black)
                          (or (zero? n) (> n 2))) :white
                     (and (= color :white)
                          (= n 2))                :black
                     :else                        color)])))))

(defn part-2
  "Day 24 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       run-instructions
       (iterate (partial step))
       (drop 100)
       first
       (count-tiles :black)))
