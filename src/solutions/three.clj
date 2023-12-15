(ns solutions.three
  (:require [clojure.string :as str]))

(defn parse-input [input]
  (->> input
       (str/split-lines)))

(defn elements-by-frequency [elems]
  (->> elems
       frequencies
       (sort-by val)
       (map first)))

(defn column [matrix c]
  (map (fn [r] (get r c)) matrix))

(defn binary-array->decimal [binary]
  (-> binary
       str/join
       (Long/parseLong 2)))

(defn solve [input]
  (let [parsed (parse-input input)
        positions (range (count (get parsed 0)))
        sorted-by-freq (->> positions
                            (map (fn [p] (column parsed p)))
                            (map elements-by-frequency))
        gamma (->> sorted-by-freq
                   (map last)
                   binary-array->decimal)
        epsilon (->> sorted-by-freq
                     (map first)
                     binary-array->decimal)]
    [(* gamma epsilon) nil]))
