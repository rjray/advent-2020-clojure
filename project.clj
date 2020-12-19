(defproject advent-of-code "0.1.0-SNAPSHOT"
  :description "Advent of Code 2020 Solutions"
  :url "https://adventofcode.com"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [instaparse "1.4.10"]
                 [criterium "0.4.6"]]
  :plugins [[lein-kibit "0.1.8"]]
  :main advent-of-code.core
  :repl-options {:init-ns advent-of-code.core})
