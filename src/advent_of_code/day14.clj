(ns advent-of-code.day14
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- decode-mem [line]
  (let [[addr val] (map #(Integer/parseInt %) (re-seq #"\d+" line))]
    (list addr val)))

(defn- parse-lines [lines]
  (reverse (reduce (fn [lst line]
                     (let [[op arg] (str/split line #" = ")]
                       (case (apply str (take 3 op))
                         "mas" (cons (list :mask (vec arg)) lst)
                         "mem" (cons (decode-mem line) lst))))
                   () lines)))

(defn- mask-value [mask value]
  (let [val (vec (take 36
                       (concat (reverse (Integer/toString value 2))
                               (repeat 36 \0))))]
    (loop [[d & val] val, [m & mask] (reverse mask), num ()]
      (cond
        (nil? d) (Long/parseLong (apply str num) 2)
        (= m \X) (recur val mask (cons d num))
        :else    (recur val mask (cons m num))))))

(defn- get-memory [sequence]
  (loop [[pair & sequence] sequence, mem {}, mask (vec (repeat 36 \X))]
    (let [[addr val] pair]
      (case addr
        nil   mem
        :mask (recur sequence mem val)
        ;; default
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
  (let [val (vec (take 36
                       (concat (reverse (Integer/toString addr 2))
                               (repeat 36 \0))))]
    (loop [[d & val] val, [m & mask] (reverse mask), num ()]
      (cond
        (nil? d) (vec num)
        (= m \0) (recur val mask (cons d num))
        (= m \1) (recur val mask (cons \1 num))
        :else    (recur val mask (cons \X num))))))

(defn- find-bits [addr]
  (reduce (fn [bits idx]
            (case (addr idx)
              \X (cons idx bits)
              bits))
          () (range (count addr))))

(defn- to-bit-str [num len]
  (vec (take len (concat (reverse (Integer/toString num 2)) (repeat len \0)))))

(defn- apply-bits [bits addr]
  (let [bv   (vec bits)
        cnt  (count bits)
        over (range (math/expt 2 cnt))
        pats (map #(to-bit-str % cnt) over)]
    (reduce (fn [addrs pat]
              (cons (reduce (fn [addr idx]
                              (assoc addr (bv idx) (pat idx)))
                            addr (range cnt)) addrs))
            () pats)))

(defn- update-mem [mem addr val mask]
  (let [addr  (mask-addr mask addr)
        bits  (find-bits addr)
        addrs (apply-bits bits addr)]
    (reduce (fn [mem addr]
              (assoc mem addr val))
            mem addrs)))

(defn- get-memory-2 [sequence]
  (loop [[pair & sequence] sequence, mem {}, mask (vec (repeat 36 \X))]
    (let [[addr val] pair]
      (case addr
        nil   mem
        :mask (recur sequence mem val)
        ;; default
        (recur sequence (update-mem mem addr val mask) mask)))))

(defn part-2
  "Day 14 Part 2"
  [input]
  (->> input
       (get-input parse-lines)
       get-memory-2
       sum-values))
