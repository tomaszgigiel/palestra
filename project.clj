(defproject palestra "1.0.0.0-SNAPSHOT"
  :description "palestra: ..."
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 ; otherwise log4j.properties has no effect
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"
  :main ^:skip-aot pl.tomaszgigiel.palestra.core

  :profiles {:uberjar {:aot :all :jar-name "palestra.jar" :uberjar-name "palestra-uberjar.jar"}})
