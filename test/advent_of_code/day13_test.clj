(ns advent-of-code.day13-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day13 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 295]
    (is (= expected (part-1 (slurp (resource "day13-example.txt")))))))

(deftest part2
  (let [expected 1068781N]
    (is (= expected (part-2 (slurp (resource "day13-example.txt")))))))
