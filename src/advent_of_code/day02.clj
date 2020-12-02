(ns advent-of-code.day02
  (:require [clojure.string :as str]))

(defn- get-lines [input]
  (str/split-lines input))

(defn- valid-password [line]
  (let [[r ch pw] (str/split line #" ")
        [lo hi]   (str/split r #"-")
        ch        (first ch)
        pwparts   (group-by identity (seq pw))
        num       (count (pwparts ch))]
    (and (<= num (Integer/parseInt hi)) (>= num (Integer/parseInt lo)))))

(defn part-1
  "Day 02 Part 1"
  [input]
  (->> input
       get-lines
       (filter valid-password)
       (count)))

(defn- valid-password2 [line]
  (let [[r ch pw] (str/split line #" ")
        [lo hi]   (str/split r #"-")
        ch        (first ch)
        pwparts   (vec (seq pw))
        chlo      (= ch (pwparts (dec (Integer/parseInt lo))))
        chhi      (= ch (pwparts (dec (Integer/parseInt hi))))]
    (and (or chlo chhi)
         (not (and chlo chhi)))))

(defn part-2
  "Day 02 Part 2"
  [input]
  (->> input
       get-lines
       (filter valid-password2)
       (count)))
