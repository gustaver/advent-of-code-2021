(ns core
  (:require [solutions.three :refer [solve]]))

(defn -main [_]
  (let [input (slurp "inputs/03.in")]
    (println (solve input))))
