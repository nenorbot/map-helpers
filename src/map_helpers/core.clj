(ns map-helpers.core)

(defn transitive-map [& maps]
  "Creates a map that is the transitive product of the maps from right to left.
  (transitive-map {:John :Mary} {:Mary :James} {:James :Jack}) => {:John :Jack}"
  (let [f (apply comp (reverse maps))
        keys (keys (first maps))]
    (into {} (map (fn [k] [k (f k)]) keys))))

(defn merge-with-keys [key-to-fn & maps]
  "Merges the maps using the key-to-fn map merge functions.
  (merge-with-keys {:add + :mul *) {:add 1 :mul 3} {:add 4 :mul 6}) => {:add 5 :mul 18}"
  (apply merge
         (for [[k f] key-to-fn]
           (apply merge-with f
                  (map #(select-keys % [k]) maps)))))

(defn filter-keys [map f]
  "Filters the map according to keys using f.
  (filter-keys {0 :0 1 :1 2 :2) even?) => {0 :0 2 :2}"
  (select-keys map (filter f (keys map))))
