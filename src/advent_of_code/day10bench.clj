(ns advent-of-code.day10bench
  (:require [advent-of-code.core :refer [read-input]]
            [clojure.string :as str]
            [criterium.core :as c]))

(defn add-endpoints [nums]
  (concat (cons 0 nums) (list (+ 3 (apply max nums)))))

(def input (->> "day10.txt"
                read-input
                str/split-lines
                (map read-string)
                add-endpoints
                sort
                vec))
(def correct 2644613988352)

;; The version I used for solving the problem. Recursion with memoization.
(defn make-solver [nums]
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

(defn solve-recursive [nums]
  (let [solver (make-solver nums)]
    (solver 0 1)))

;; The two dynamic-programming approaches below are adapted from the sample
;; Python code in this reddit comment:
;; https://old.reddit.com/r/adventofcode/comments/ka8z8x/2020_day_10_solutions/gfal951/

;; Top-down dynamic programming approach, using recursion and memoization. Note
;; that the Python example of this method in the above link passed an array
;; along that was updated with aech sub-step's result. Here, instead, I'm using
;; memoize around a single-argument function. This will intercept any subsequent
;; calls to the recursive parts.
(defn make-dp-top-down-solver [nums]
  (def solver
    (memoize
     (fn [i]
       (if (zero? i)
         1
         (let [js  (filter #(<= 0 (- i %)) (range 1 4))
               js' (filter #(<= (- (nums i) (nums (- i %))) 3) js)]
           (apply + (map #(solver (- i %)) js'))))))))

(defn solve-dp-top-down [nums]
  (let [solver (make-dp-top-down-solver nums)]
    (solver (dec (count nums)))))

;; Bottom-up dynamic programming approach, no recursion and a running vector
;; of results.
(defn dp-bottom-up [len tbl nums]
  (last (reduce (fn [tbl i]
                  (let [js  (filter #(<= 0 (- i %)) (range 1 4))
                        js' (filter #(<= (- (nums i) (nums (- i %))) 3) js)
                        sum (apply + (map #(tbl (- i %)) js'))]
                    (assoc tbl i sum)))
                tbl (range 1 len))))

(defn solve-dp-bottom-up [nums]
  (let [len (count nums)
        tbl (assoc (vec (repeat len 0)) 0 1)]
    (dp-bottom-up len tbl nums)))

(defn test-all []
  (do
    (if (= correct (solve-recursive input))
      (println "solve-recursive is correct")
      (println "error in solve-recursive"))
    (if (= correct (solve-dp-top-down input))
      (println "solve-dp-top-down is correct")
      (println "error in solve-dp-top-down"))
    (if (= correct (solve-dp-bottom-up input))
      (println "solve-dp-bottom-up is correct")
      (println "error in solve-dp-bottom-up"))))

(defn bench-all []
  (println "Recursive benching:")
  (c/quick-bench (solve-recursive input))
  (println)
  (println "DP top-down benching:")
  (c/quick-bench (solve-dp-top-down input))
  (println)
  (println "DP bottom-up benching:")
  (c/quick-bench (solve-dp-bottom-up input)))
