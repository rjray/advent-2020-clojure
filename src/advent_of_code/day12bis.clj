(ns advent-of-code.day12bis
  (:require [clojure.string :as str]))

(defn- process-input [input]
  (->> input
       (re-seq #"(?:([NSEWLRF])(\d+))")
       (map #(list (ffirst %) (Integer/parseInt (last %))))))

(defn- move-point [[x y] dir dist]
  (case dir
    \N (list x (+ y dist))
    \S (list x (- y dist))
    \E (list (+ x dist) y)
    \W (list (- x dist) y)))

(defn- turn-boat [facing dir value]
  (let [value   (/ (if (= dir \L) (- value) value) 90)
        current ({\N 0, \E 1, \S 2, \W 3} facing)]
    ([\N \E \S \W] (mod (+ current value) 4))))

(defn- calc-position [moves]
  (loop [[[action value] & moves] moves, facing \E, pos (list 0 0)]
    (case action
      nil     pos
      \F      (recur moves facing (move-point pos facing value))
      (\L \R) (recur moves (turn-boat facing action value) pos)
      ;; default
      (recur moves facing (move-point pos action value)))))

(defn part-1
  "Day 12 Part 1"
  [input]
  (->> input
       process-input
       calc-position
       (map #(Math/abs %))
       (apply +)))

(defn- move-by-waypoint [[x y] [x' y'] times]
  (list (+ x (* times x')) (+ y (* times y'))))

(defn- turn-waypoint [way dir value]
  (let [value   (/ value 90)]
    (reduce (fn [pos _]
              (if (= dir \R)
                (list (last pos) (- (first pos)))
                (list (- (last pos)) (first pos))))
            way (range value))))

(defn- calc-by-waypoint [moves]
  (loop [[[action value] & moves] moves,
         pos                      (list 0 0),
         way                      (list 10 1)]
    (case action
      nil     pos
      \F      (recur moves (move-by-waypoint pos way value) way)
      (\L \R) (recur moves pos (turn-waypoint way action value))
      ;; default
      (recur moves pos (move-point way action value)))))

(defn part-2
  "Day 12 Part 2"
  [input]
  (->> input
       process-input
       calc-by-waypoint
       (map #(Math/abs %))
       (apply +)))
