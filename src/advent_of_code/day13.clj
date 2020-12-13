(ns advent-of-code.day13
  (:require [clojure.string :as str]
            [clojure.set :as sets]
            [clojure.math.numeric-tower :as math]))

(defn- process-input [input]
  (->> input
       str/split-lines))

(defn- content [lines]
  (list (Integer/parseInt (first lines))
        (map #(Integer/parseInt %)
             (filter #(not= % "x") (str/split (last lines) #",")))))

(defn- find-first-starts [min-time buses]
  (reduce (fn [tbl bus]
            (assoc tbl bus (* (inc (int (/ min-time bus))) bus)))
          {} buses))

(defn- find-earliest-vals [[min-time buses]]
  (let [times (sets/map-invert (find-first-starts min-time buses))
        best  (apply min (keys times))]
    (list min-time (times best) best)))

(defn- calc-answer [[wait-time bus-id depart-time]]
  (* bus-id (- depart-time wait-time)))

(defn part-1
  "Day 13 Part 1"
  [input]
  (->> input
       process-input
       content
       find-earliest-vals
       calc-answer))

(defn- make-list [string]
  (let [in (vec (str/split string #","))]
    (reverse (reduce (fn [lst idx]
                       (if (= (in idx) "x")
                         lst
                         (let [val (Integer/parseInt (in idx))]
                           (cons (list (- val idx) val) lst))))
                     () (range (count in))))))

(defn- find-min-timestamp [pairs]
  (let [modulo (apply * (map last pairs))]
    (reduce (fn [total [x mx]]
              (let [b (quot modulo mx)]
                (mod (+ total (* x b (mod (math/expt b (- mx 2)) mx)))
                     modulo)))
            0 pairs)))

(defn part-2
  "Day 13 Part 2"
  [input]
  (->> input
       process-input
       last
       make-list
       find-min-timestamp))
