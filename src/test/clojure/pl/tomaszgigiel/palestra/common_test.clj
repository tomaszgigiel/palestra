(ns pl.tomaszgigiel.palestra.common-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.common :as my-common])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest ok-test (testing "ok" (is (= 0 0))))
