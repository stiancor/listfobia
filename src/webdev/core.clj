(ns webdev.core
	(:require [webdev.item.model :as items])
	(:require [ring.adapter.jetty :as jetty]
			  [ring.middleware.reload :refer [wrap-reload]]
			  [compojure.core :refer [defroutes GET]]
			  [compojure.route :refer [not-found]]
			  [ring.handler.dump :refer [handle-dump]]))

(def db "jdbc:postgresql://localhost/webdev")

(defn greet [req] 
		{:status 200 :body "Hello World!" :headers {}})

(defn about [req] 
		{:status 200 :body "<h1>About</h1> <p>The only purpose of this site is to learn some web development using Clojure.<p>" :headers {}})

(defn goodbye [req] 
		{:status 200 :body "Goodbye, Cruel World!" :headers {}})

(defn yo [req]
	(let [name (get-in req [:route-params :name])] 
		{:status 200 
		 :body (str "Yo " name "!")
		 :headers {}}))

(def operators
	{"+" +
	 "-" -
	 "*" *
	 ":" /})

(defn calc [req]
	(let [a (Integer. (get-in req [:route-params :a])) 
	      b (Integer. (get-in req [:route-params :b]))
	 	  operator (get-in req [:route-params :operator]) 
	 	  f (get operators operator)]
	 	(if f 
	 	  	{:status 200 
		 	 :body (str (f a b))
		 	 :headers {}}
		 	{:status 404 
		 	 :body (str "Unknown operator: " operator)
		 	 :headers {}})))

(defroutes app
	(GET "/" [] greet)
	(GET "/about" [] about)
	(GET "/goodbye" [] goodbye)
	(GET "/request" [] handle-dump)
	(GET "/yo/:name" [] yo)
	(GET "/calc/:a/:operator/:b" [] calc)
	(not-found "Page not found!"))

(defn -main [port]
	(items/create-table db)
	(jetty/run-jetty app {:port (Integer. port)}))

(defn -dev-main [port]
	(items/create-table db)
	(jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))