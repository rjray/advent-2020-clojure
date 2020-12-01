(ns advent-of-code.day01
  (:require [clojure.math.combinatorics :as comb]))

(defn- read-numbers [data]
  (->> data
       (re-seq #"-?\d+")
       (map #(Integer/parseInt %))))

(defn part-1
  "Day 01 Part 1"
  [input]
  (as-> input $
    (read-numbers $)
    (comb/combinations $ 2)
    (filter #(= 2020 (apply + %)) $)
    (apply * (first $))))

(defn part-2
  "Day 01 Part 2"
  [input]
  (as-> input $
    (read-numbers $)
    (comb/combinations $ 3)
    (filter #(= 2020 (apply + %)) $)
    (apply * (first $))))
