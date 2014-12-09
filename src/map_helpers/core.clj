(ns map-helpers.core
  (:require [clojure.math.combinatorics :as combi]))

(defn transitive-map
  "Creates a map that is the transitive product of the maps from left to right.
  (transitive-map {:John :Mary} {:Mary :James} {:James :Jack}) => {:John :Jack}"
  [& maps]
  (let [f (apply comp (reverse maps))
        keys (keys (first maps))]
    (zipmap keys (map f keys))))

(defn merge-with-keys
  "Merges the maps using the key-to-fn map merge functions.
  (merge-with-keys {:add + :mul *) {:add 1 :mul 3} {:add 4 :mul 6}) => {:add 5 :mul 18}"
  [key-to-fn & maps]
  (apply merge
         (for [[k f] key-to-fn]
           (apply merge-with f
                  (map #(select-keys % [k]) maps)))))

(defn filter-keys
  "Filters the map according to keys using f.
  (filter-keys {0 :0 1 :1 2 :2) even?) => {0 :0 2 :2}"
  [map f]
  (select-keys map (filter f (keys map))))

(defn product
  "Given a map mapping keys to collections, returns a new lazy sequence of
  maps, each containing the different cartesin products between said
  keys to the values in the collections.
  (product {:a [1 2] :b [3 4]}) => ({:a 1, :b 3},
  {:a 1, :b 4}, {:a 2 :b 3}, {:a 2 :b 4})"
  [m]
  (let [keys (keys m)
        products (apply combi/cartesian-product
                        (map m keys))]
    (map #(zipmap keys %) products)))
