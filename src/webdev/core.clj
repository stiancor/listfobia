(ns webdev.core
	(:require [ring.adapter.jetty :as jetty]
			  [ring.middleware.reload :refer [wrap-reload]]
			  [compojure.core :refer [defroutes GET]]
			  [compojure.route :refer [not-found]]
			  [ring.handler.dump :refer [handle-dump]]))

(defn greet [req] 
		{:status 200 :body "Hello World!" :headers {}})

(defn about [req] 
		{:status 200 :body "<h1>About</h1> <p>The only purpose of this site is to learn some web development using Clojure.<p>" :headers {}})

(defn goodbye [req] 
		{:status 200 :body "Goodbye, Cruel World!" :headers {}})

(defroutes app
	(GET "/" [] greet)
	(GET "/about" [] about)
	(GET "/goodbye" [] goodbye)
	(GET "/request" [] handle-dump)
	(not-found "Page not found!"))

(defn -main [port]
	(jetty/run-jetty app {:port (Integer. port)}))

(defn -dev-main [port]
	(jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))