(ns advent-of-code.day10bis
  (:require [clojure.string :as str]))

(defn- add-endpoints [nums]
  (concat (cons 0 nums) (list (+ 3 (apply max nums)))))

;; Replaced (group-by identity ...) with (frequencies ...)
(defn- count-gaps [nums]
  (frequencies
   (map #(apply - (reverse %))
        (partition 2 1 (sort nums)))))

;; This no longer needed (count ...)
(defn- mult [k1 k2 counts] (* (counts k1) (counts k2)))

(defn part-1
  "Day 10 Part 1"
  [input]
  (->> input
       str/split-lines
       (map read-string)
       add-endpoints
       count-gaps
       (mult 1 3)))

;; Getting this right is due in large part to seeing how Vincent Cantin
;; solved part 2.
;; (https://github.com/green-coder/advent-of-code-2020/blob/master/src/aoc/day_10.clj).

;; Moved the binding of maxidx outside of the def, since it doesn't change
;; even in the recursive memoizations.
(defn- make-solver [nums]
  (let [maxidx (dec (count nums))]
    (def solver
      (memoize
       (fn [prev idx]
         (if (< idx maxidx)
           (let [val   (nums idx)
                 nextv (nums (inc idx))]
             (if (<= (- nextv prev) 3)
               (+ (solver val (inc idx))
                  (solver prev (inc idx)))
               (solver val (inc idx))))
           1))))))

(defn part-2
  "Day 10 Part 2"
  [input]
  (as-> input $
    (str/split-lines $)
    (map read-string $)
    (add-endpoints $)
    (sort $)
    (vec $)
    (make-solver $)
    ($ 0 1)))
