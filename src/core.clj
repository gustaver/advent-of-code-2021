(ns core
  (:require [solutions.two :refer [solve]]))

(defn -main [_]
  (let [input (slurp "inputs/02.in")]
    (println (solve input))))