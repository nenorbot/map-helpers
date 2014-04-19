(ns map-helpers.core-test
  (:require [clojure.test :refer :all]
            [map-helpers.core :refer :all]))

(deftest transitive-map-test
  (let [a {:a :aa :b :bb :c :cc}
        b {:aa :aaa :cc :ccc}
        c {:aaa :aaaa :bbb :bbbb}
        transitive (transitive-map a b c)]
    (is (= :aaaa (:a transitive)))
    (is (nil? (:b transitive)))
    (is (nil? (:c transitive)))))

(deftest merge-with-keys-test
  (let [merged (merge-with-keys
                {:add + :mul *} {:add 1 :mul 2} {:add 3 :mul 5})]
    (is (= 4 (:add merged)))
    (is (= 10 (:mul merged)))))

(deftest filter-keys-test
  (let [evens-map (filter-keys {0 0 1 1 2 2 3 3} even?)]
    (is (= evens-map {0 0 2 2}))))
