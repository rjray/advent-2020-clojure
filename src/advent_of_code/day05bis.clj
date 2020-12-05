(ns advent-of-code.day05bis
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math]))

(defn- bin-partition [parts]
  (let [hi (dec (math/expt 2 (count parts)))]
    (loop [[part & parts] parts, lo 0, hi hi]
      (let [gap (/ (+ lo hi 1) 2)]
        (cond
          (nil? part) lo
          (= part \F) (recur parts lo (- gap 1))
          (= part \L) (recur parts lo (- gap 1))
          (= part \B) (recur parts gap hi)
          (= part \R) (recur parts gap hi))))))

(defn- get-id [pass]
  (let [row  (take 7 pass)
        seat (drop 7 pass)]
    (+ (* 8 (bin-partition row)) (bin-partition seat))))

(defn part-1
  "Day 05 Part 1"
  [input]
  (->> input
       str/split-lines
       (map get-id)
       sort
       last))

(defn- find-gap [lst]
  (let [[init & lst] lst]
    (loop [[l & lst] lst, init init]
      (cond
        (nil? l)         nil
        (= (inc init) l) (recur lst l)
        :else            (dec l)))))

(defn part-2
  "Day 05 Part 2"
  [input]
  (->> input
       str/split-lines
       (map get-id)
       sort
       find-gap))
