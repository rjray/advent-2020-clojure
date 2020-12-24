(ns advent-of-code.day23bis
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (-> input
      str/trimr
      (str/split #"")
      func))

(defn- parse-data [lines] (map #(Integer/parseInt %) lines))

(defn- make-linked [size input]
  (let [cnt    (count input)
        linked (vec (range 1 (+ 2 size)))
        linked (assoc (reduce (fn [lnkd [a b]]
                                (assoc lnkd a b))
                              linked (partition 2 1 input))
                      0 (first input))]
    (if (= size cnt)
      (assoc linked (last input) (first input))
      (assoc linked (last input) (inc (apply max input)) size (first input)))))

(defn- find-destination [val seen size]
  (loop [val val]
    (cond
      (seen val) (recur (mod (dec val) size))
      :else      val)))

(defn- adjust [state size]
  (let [cur  (state 0)
        a    (state cur)
        b    (state a)
        c    (state b)
        n    (state c)
        cval (find-destination (dec cur) #{a b c 0} size)
        ins  (state cval)]
    (assoc state 0 n, cur n, cval a, c ins)))

(defn- run-game [n start]
  (let [size (count start)]
    (reduce (fn [state _]
              (adjust state size)) start (range n))))

(defn- get-answer-1 [final]
  (let [num (- (count final) 2)]
    (str/join (rest (reverse (reduce (fn [lst _]
                                       (cons (final (first lst)) lst))
                                     (list 1) (range num)))))))

(defn part-1
  "Day 23 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       (make-linked 9)
       (run-game 100)
       get-answer-1))

(defn- get-answer-2 [game] (* (game 1) (game (game 1))))

(defn part-2
  "Day 23 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       (make-linked 1000000)
       (run-game 10000000)
       get-answer-2))
