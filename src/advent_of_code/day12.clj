(ns advent-of-code.day12
  (:require [clojure.string :as str]))

(defn- process-input [input]
  (->> input
       (re-seq #"(?:([NSEWLRF])(\d+))")
       (map #(list (ffirst %) (Integer/parseInt (last %))))))

(defn- adjust [[x y] dir dist]
  (case dir
    \N (list x (+ y dist))
    \S (list x (- y dist))
    \E (list (+ x dist) y)
    \W (list (- x dist) y)))

(defn- turn [facing dir value]
  (let [value   (/ value 90)
        value   (if (= dir \L) (- value) value)
        order   [\N \E \S \W]
        omap    {\N 0, \E 1, \S 2, \W 3}
        current (omap facing)]
    (order (mod (+ current value) 4))))

(defn- calc-position [moves]
  (loop [[[action value] & moves] moves, facing \E, pos (list 0 0)]
    (cond
      (nil? action)      pos
      (= action \F)      (recur moves facing (adjust pos facing value))
      (or (= action \L)
          (= action \R)) (recur moves (turn facing action value) pos)
      :else              (recur moves facing (adjust pos action value)))))

(defn part-1
  "Day 12 Part 1"
  [input]
  (->> input
       process-input
       calc-position
       (map #(Math/abs %))
       (apply +)))

(defn- adjust2 [[x y] [x' y'] times]
  (list (+ x (* times x')) (+ y (* times y'))))

(defn- turn2 [way dir value]
  (let [value   (/ value 90)]
    (reduce (fn [pos n]
              (if (= dir \R)
                (list (last pos) (- (first pos)))
                (list (- (last pos)) (first pos))))
            way (range value))))

(defn- calc-position2 [moves]
  (loop [[[action value] & moves] moves,
         pos                      (list 0 0),
         way                      (list 10 1)]
    (cond
      (nil? action)      pos
      (= action \F)      (recur moves (adjust2 pos way value) way)
      (or (= action \L)
          (= action \R)) (recur moves pos (turn2 way action value))
      :else              (recur moves pos (adjust way action value)))))

(defn part-2
  "Day 12 Part 2"
  [input]
  (->> input
       process-input
       calc-position2
       (map #(Math/abs %))
       (apply +)))
