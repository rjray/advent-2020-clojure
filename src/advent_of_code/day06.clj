(ns advent-of-code.day06
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn- count-group [group]
  (count (set (re-seq #"[a-z]" group))))

(defn- count-by-group [groups]
  (map count-group groups))

(defn part-1
  "Day 06 Part 1"
  [input]
  (as-> input $
    (str/split $ #"\n\n")
    (count-by-group $)
    (apply + $)))

(defn- get-intersect [group]
  (->> group
       str/split-lines
       (map #(set (re-seq #"[a-z]" %)))
       (apply set/intersection)
       count))

(defn- get-intersects [groups]
  (map get-intersect groups))

(defn part-2
  "Day 06 Part 2"
  [input]
  (as-> input $
    (str/split $ #"\n\n")
    (get-intersects $)
    (apply + $)))
