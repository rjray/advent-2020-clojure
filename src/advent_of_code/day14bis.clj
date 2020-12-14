(ns advent-of-code.day14bis
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- to-bit-vec [num & [len]]
  (let [len (or len 36)]
    (vec (take len (concat (reverse (Long/toBinaryString num))
                           (repeat len \0))))))

(defn- decode-mem [line]
  (let [[addr val] (map #(Long/parseLong %) (re-seq #"\d+" line))]
    (list addr val)))

(defn- parse-lines [lines]
  (reverse (reduce (fn [lst line]
                     (let [[op arg] (str/split line #" = ")]
                       (case (apply str (take 3 op))
                         "mas" (cons (list :mask (vec arg)) lst)
                         "mem" (cons (decode-mem line) lst))))
                   () lines)))

(defn- mask-value [mask value]
  (let [val (to-bit-vec value)]
    (loop [[d & val] val, [m & mask] (reverse mask), num ()]
      (case m
        nil (Long/parseLong (apply str num) 2)
        \X  (recur val mask (cons d num))
        (recur val mask (cons m num))))))

(defn- get-memory [sequence]
  (loop [[pair & sequence] sequence, mem {}, mask (vec (repeat 36 \X))]
    (let [[addr val] pair]
      (case addr
        nil   mem
        :mask (recur sequence mem val)
        (recur sequence (assoc mem addr (mask-value mask val)) mask)))))

(defn- sum-values [mem]
  (apply + (vals mem)))

(defn part-1
  "Day 14 Part 1"
  [input]
  (->> input
       (get-input parse-lines)
       get-memory
       sum-values))

(defn- mask-addr [mask addr]
  (let [val (to-bit-vec addr)]
    (loop [[d & val] val, [m & mask] (reverse mask), num ()]
      (case m
        nil (apply str num)
        \0  (recur val mask (cons d num))
        \1  (recur val mask (cons \1 num))
        (recur val mask (cons \X num))))))

(defn- fill-bits [addr]
  (let [cnt  (count (re-seq #"X" addr))
        pats (map #(to-bit-vec % cnt) (range (math/expt 2 cnt)))]
    (reduce (fn [addrs pat]
              (cons (reduce (fn [s bit]
                              (str/replace-first s \X bit))
                            addr pat)
                    addrs))
            () pats)))

(defn- update-mem [mem addr val mask]
  (let [addr  (mask-addr mask addr)
        addrs (fill-bits addr)]
    (reduce (fn [mem addr]
              (assoc mem addr val))
            mem addrs)))

(defn- get-memory-2 [sequence]
  (loop [[pair & sequence] sequence, mem {}, mask (vec (repeat 36 \X))]
    (let [[addr val] pair]
      (case addr
        nil   mem
        :mask (recur sequence mem val)
        (recur sequence (update-mem mem addr val mask) mask)))))

(defn part-2
  "Day 14 Part 2"
  [input]
  (->> input
       (get-input parse-lines)
       get-memory-2
       sum-values))
