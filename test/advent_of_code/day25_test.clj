(ns advent-of-code.day25-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day25 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 14897079]
    (is (= expected (part-1 (slurp (resource "day25-example.txt")))))))
