(ns utils
  (:require [clojure.string :as str]
            [test :refer [is is-not is=]]))

(defn str->int [s]
  (java.lang.Integer/parseInt s))

(defn split-whitespace [s]
  (str/split s #"\s+"))

(defn split-comma [s]
  (str/split s #","))

(defn all=?
  "Returns true/false if all elements in a collection are equal"
  {:test (fn []
           (is (all=? [1 1 1]))
           (is (all=? '(1 1 1)))
           (is-not (all=? #{1 2 3}))
           (is-not (all=? [1 2 3])))}
  [coll]
  (let [[x & rest] coll]
    (every? (partial = x) rest)))

(defn sum
  "Sums the element in collection instead of using (apply + coll)"
  [coll]
  (apply + coll))

(defn find-first
  "Returns the first element in collection that satisfied predicate"
  {:test (fn []
           (is= :three (find-first keyword? [1 2 :three 4]))
           (is= 2 (find-first even? [1 2 3 4])))}
  [pred coll]
  (first (filter pred coll)))

(defn count-cond
  "Returns the number of elements in a collection which satisfy the predicate"
  {:test (fn []
           (is= 2 (count-cond even? [1 2 3 4])))}
  [pred coll]
  (count (filter pred coll)))
