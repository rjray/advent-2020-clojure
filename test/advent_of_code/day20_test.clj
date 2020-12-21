(ns advent-of-code.day20-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day20 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 20899048083289]
    (is (= expected (part-1 (slurp (resource "day20-example.txt")))))))

(deftest part2
  (let [expected 273]
    (is (= expected (part-2 (slurp (resource "day20-example.txt")))))))
