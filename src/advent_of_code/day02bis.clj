(ns advent-of-code.day02bis
  (:require [advent-of-code.core :refer [read-input]]
            [clojure.string :as str]))

(defn- valid-password [line]
  (let [[_ lo hi [ch] pw] (re-find #"(\d+)-(\d+)\s(\w):\s(\w+)" line)
        pwparts   (group-by identity (seq pw))
        num       (count (pwparts ch))]
    (<= (Integer/parseInt lo) num (Integer/parseInt hi))))

(defn part-1
  "Day 02 Part 1"
  []
  (->> "day02.txt"
       read-input
       str/split-lines
       (filter valid-password)
       (count)))

(defn- valid-password2 [line]
  (let [[_ lo hi [ch] pw] (re-find #"(\d+)-(\d+)\s(\w):\s(\w+)" line)
        pwparts   (vec pw)
        chlo      (= ch (pwparts (dec (Integer/parseInt lo))))
        chhi      (= ch (pwparts (dec (Integer/parseInt hi))))]
    (and (or chlo chhi)
         (not (and chlo chhi)))))

(defn part-2
  "Day 02 Part 2"
  []
  (->> "day02.txt"
       read-input
       str/split-lines
       (filter valid-password2)
       (count)))
