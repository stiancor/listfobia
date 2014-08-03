(defproject webdev "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				 [ring "1.3.0"]
  				 [compojure "1.1.8"]
  				 [org.clojure/java.jdbc "0.3.4"]
  				 [postgresql/postgresql "9.1-901-1.jdbc4"]]

:min-lein-version "2.0.0"

:uberjar-name "webdev.jar"

:main webdev.core

:profiles {:dev 
			{:main webdev.core/-dev-main}})
