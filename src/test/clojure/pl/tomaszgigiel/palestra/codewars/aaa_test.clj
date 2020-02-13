(ns pl.tomaszgigiel.palestra.codewars.aaa-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.aaa :as aaa])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest ok-test
  (testing "aaa"
           (is (= (aaa/aaa 1) 1))))
