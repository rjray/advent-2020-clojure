(ns advent-of-code.day25
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- parse-data [lines]
  [(read-string (first lines)) (read-string (last lines))])

(defn- iteration [value subjnum]
  (rem (* value subjnum) 20201227))

(defn- find-loops [target]
  (loop [n 1, value 1]
    (cond
      (= value target) (dec n)
      :else            (recur (inc n) (iteration value 7)))))

(defn- transform [value loops]
  (reduce (fn [x _]
            (iteration x value ))
          1 (range loops)))

(defn- find-key [[card door]]
  (let [loops (find-loops card)]
    (transform door loops)))

(defn part-1
  "Day 25 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       find-key))

;; No part 2 on day 25.
