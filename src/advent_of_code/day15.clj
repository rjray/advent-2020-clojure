(ns advent-of-code.day15
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- get-nums [lines]
  (map #(Integer/parseInt %) (re-seq #"\d+" (first lines))))

(defn- get-pos [target nums]
  (let [history (zipmap (butlast nums) (range 1 (count nums)))]
    (loop [idx (count nums), lastnum (last nums), hist history]
      (let [firstspoken (not (contains? hist lastnum))
            nextnum     (if firstspoken 0 (- idx (hist lastnum)))]
        (cond
          (= idx target) lastnum
          :else          (recur (inc idx) nextnum (assoc hist lastnum idx)))))))

(defn part-1
  "Day 15 Part 1"
  [input]
  (->> input
       (get-input get-nums)
       (get-pos 2020)))

(defn part-2
  "Day 15 Part 2"
  [input]
  (->> input
       (get-input get-nums)
       (get-pos 30000000)))
