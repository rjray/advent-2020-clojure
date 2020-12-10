(ns advent-of-code.day10
  (:require [clojure.string :as str]))

(defn- add-endpoints [nums]
  (concat (cons 0 nums) (list (+ 3 (apply max nums)))))

(defn- count-gaps [nums]
  (group-by identity
            (map #(apply - (reverse %))
                 (partition 2 1 (sort nums)))))

(defn- mult [k1 k2 counts]
  (* (count (counts k1)) (count (counts k2))))

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

(defn- make-solver [nums]
  (def solve
    (let [maxidx (dec (count nums))]
      (memoize
       (fn [prev idx]
         (if (< idx maxidx)
           (let [val      (nums idx)
                 next-val (nums (inc idx))]
             (if (<= (- next-val prev) 3)
               (+ (solve val (inc idx))
                  (solve prev (inc idx)))
               (solve val (inc idx))))
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
