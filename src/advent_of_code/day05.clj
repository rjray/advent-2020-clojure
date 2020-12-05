(ns advent-of-code.day05
  (:require [clojure.string :as str]))

(defn- bin-partition-rows [parts]
  (loop [[part & parts] parts, lo 0, hi 127]
    (let [gap (/ (+ lo hi 1) 2)]
      (cond
        (nil? part) lo
        (= part \F) (recur parts lo (- gap 1))
        (= part \B) (recur parts gap hi)))))

(defn- bin-partition-seats [parts]
  (loop [[part & parts] parts, lo 0, hi 7]
    (let [gap (/ (+ lo hi 1) 2)]
      (cond
        (nil? part) lo
        (= part \L) (recur parts lo (- gap 1))
        (= part \R) (recur parts gap hi)))))

(defn- get-id [pass]
  (let [row  (take 7 pass)
        seat (reverse (take 3 (reverse pass)))]
    (+ (* 8 (bin-partition-rows row)) (bin-partition-seats seat))))

(defn part-1
  "Day 05 Part 1"
  [input]
  (->> input
       str/split-lines
       (map get-id)
       sort
       last))

(defn- find-gaps [lst]
  (let [[init & lst] lst]
    (loop [[l & lst] lst, init init, gaps ()]
      (cond
        (nil? l) gaps
        (= (inc init) l) (recur lst l gaps)
        :else            (recur lst l (cons (dec l) gaps))))))

(defn part-2
  "Day 05 Part 2"
  [input]
  (->> input
       str/split-lines
       (map get-id)
       sort
       (drop 8)
       reverse
       (drop 8)
       reverse
       find-gaps))
