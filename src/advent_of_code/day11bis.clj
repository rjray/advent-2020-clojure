(ns advent-of-code.day11bis
  (:require [clojure.string :as str]))

(def surrounds [[-1 -1] [-1 0] [-1 1]
                [0 -1]         [0 1]
                [1 -1]  [1 0]  [1 1]])

(defn- process-input [input]
  (->> input
       str/split-lines
       (mapv vec)))

(defn- count-occupied [floor]
  ((frequencies (flatten floor)) \#))

(defn- valid [[y x] maxy maxx]
  (and (< -1 y maxy) (< -1 x maxx)))

(defn- count-surrounds [floor y x]
  (let [maxy    (count floor)
        maxx    (count (first floor))
        squares (for [move surrounds] (mapv + [y x] move))]
    (apply + (for [square squares]
               (if (= (get-in floor square) \#) 1 0)))))

(defn- change-to [floor y x pred tol]
  (let [cur (get-in floor [y x])
        sur (pred floor y x)]
    (case cur
      \L (if (zero? sur) \# \L)
      \# (if (>= sur tol) \L \#)
      cur)))

(defn- transform [floor pred tol seats]
  (reduce (fn [fl [y x]]
            (assoc-in fl [y x] (change-to floor y x pred tol)))
          floor seats))

(defn- run-sim [pred tol floor]
  (let [maxy  (count floor)
        maxx  (count (first floor))
        seats (for [x (range maxx), y (range maxy)
                    :when (not= \. (get-in floor [y x]))]
                [y x])]
    (loop [floor floor, prev nil]
      (cond
        (= floor prev) floor
        :else          (recur (transform floor pred tol seats) floor)))))

(defn part-1
  "Day 11 Part 1"
  [input]
  (->> input
       process-input
       (run-sim count-surrounds 4)
       count-occupied))

(defn- visible-seat? [floor dir y x]
  (let [start (mapv + [y x] dir)]
    (loop [pos start]
      (case (get-in floor pos)
        \# true
        \. (recur (mapv + pos dir))
        false))))

(defn- count-visible [floor y x]
  (let [maxy (count floor)
        maxx (count (first floor))
        dirs (filter #(valid (mapv + [y x] %) maxy maxx) surrounds)]
    (apply + (for [dir dirs]
               (if (visible-seat? floor dir y x) 1 0)))))

(defn part-2
  "Day 11 Part 2"
  [input]
  (->> input
       process-input
       (run-sim count-visible 5)
       count-occupied))
