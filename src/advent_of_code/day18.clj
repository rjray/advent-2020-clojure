(ns advent-of-code.day18
  (:require [clojure.string :as str]
            [clojure.edn :as edn]
            [clojure.walk :as walk]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- parse-data [lines]
  (mapv (fn [line]
          (edn/read-string (str "(" line ")"))) lines))

(defn- calc-expr [line]
  (walk/postwalk (fn [x]
                   (if (sequential? x)
                     (reduce (fn [accum [op arg]]
                               (case op
                                 + (+ accum arg)
                                 * (* accum arg)))
                             (first x)
                             (partition 2 (next x)))
                     x))
                 line))

(defn part-1
  "Day 18 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       (map calc-expr)
       (reduce +)))

(defn- add-stuff [line]
  (walk/postwalk (fn [x]
                   (if (list? x)
                     (->> x
                          (partition-by #{'*})
                          (map (fn [x]
                                 (if (= '(*) x) '* x))))
                     x))
                 line))

(defn part-2
  "Day 18 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       (map add-stuff)
       (map calc-expr)
       (reduce +)))
