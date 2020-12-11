(ns advent-of-code.day11
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

(defn- change-to [floor y x]
  (let [cur (get-in floor [y x])
        sur (count-surrounds floor y x)]
    (cond
      (and (= cur \L) (zero? sur)) \#
      (and (= cur \#) (>= sur 4))  \L
      :else                        cur)))

(defn- transform [floor]
  (let [maxy  (count floor)
        maxx  (count (first floor))
        seats (for [x (range maxx), y (range maxy)
                    :when (not= \. (get-in floor [y x]))]
                [y x])]
    (reduce (fn [fl [y x]]
              (assoc-in fl [y x] (change-to floor y x)))
            floor seats)))

(defn- run-sim [floor]
  (loop [floor floor, prev nil]
    (cond
      (= floor prev) floor
      :else          (recur (transform floor) floor))))

(defn part-1
  "Day 11 Part 1"
  [input]
  (->> input
       process-input
       run-sim
       count-occupied))

(defn- visible-seat? [floor dir y x]
  (let [start (mapv + [y x] dir)]
    (loop [pos start]
      (let [ch (get-in floor pos)]
        (cond
          (nil? ch) false
          (= ch \.) (recur (mapv + pos dir))
          (= ch \#) true
          :else     false)))))

(defn- count-visible [floor y x]
  (let [maxy (count floor)
        maxx (count (first floor))
        dirs (filter #(valid (mapv + [y x] %) maxy maxx) surrounds)]
    (apply + (for [dir dirs]
               (if (visible-seat? floor dir y x) 1 0)))))

(defn- change-to2 [floor y x]
  (let [cur (get-in floor [y x])
        sur (count-visible floor y x)]
    (cond
      (and (= cur \L) (zero? sur)) \#
      (and (= cur \#) (>= sur 5))  \L
      :else                        cur)))

(defn- transform2 [floor]
  (let [maxy  (count floor)
        maxx  (count (first floor))
        seats (for [x (range maxx), y (range maxy)
                    :when (not= \. (get-in floor [y x]))]
                [y x])]
    (reduce (fn [fl [y x]]
              (assoc-in fl [y x] (change-to2 floor y x)))
            floor seats)))

(defn- run-sim2 [floor]
  (loop [floor floor, prev nil]
    (cond
      (= floor prev) floor
      :else          (recur (transform2 floor) floor))))

(defn part-2
  "Day 11 Part 2"
  [input]
  (->> input
       process-input
       run-sim2
       count-occupied))
