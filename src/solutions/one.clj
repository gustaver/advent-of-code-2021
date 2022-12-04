(ns solutions.one
  (:require [clojure.string :as str]
            [utils :refer [str->int]]))

(defn count-increases [xs]
  (->> (map < xs (rest xs))
       (filter identity)
       (count)))

(defn solve [input]
  (let [measurements (->> input
                          (str/split-lines)
                          (map str->int))]
    {:p1 (count-increases measurements)
     :p2 (->> (map + measurements (rest measurements) (rest (rest measurements)))
              (count-increases))}))