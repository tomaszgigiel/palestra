(ns pl.tomaszgigiel.palestra.core-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.core :as my-core])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest ok-test (testing "ok" (is (= 0 0))))
