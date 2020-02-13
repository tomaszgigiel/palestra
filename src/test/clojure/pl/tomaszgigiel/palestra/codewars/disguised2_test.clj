(ns pl.tomaszgigiel.palestra.codewars.disguised2-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.disguised2 :as disguised2])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(defn test-assert [act exp]
  (is (= act exp)))

(deftest a-test1
  (testing "u1 v1"   
       (test-assert (disguised2/v1 19 11) 429N)
       (test-assert (disguised2/v1 18, 144) 5328N)
       (test-assert (disguised2/v1 12, 70) 1750N)
       (test-assert (disguised2/v1 99 11) 2189N)
       (test-assert (disguised2/v1 999 11) 21989N)
       (test-assert (disguised2/u1 19, 11) 220N)
       (test-assert (disguised2/u1 3, 69) 276N)
       (test-assert (disguised2/u1 13 18) 252N)
       (test-assert (disguised2/u1 99 18) 1800N)
       (test-assert (disguised2/u1 999 18) 18000N)
))
