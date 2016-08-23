(ns generative_art_tut.core
  (:require [generative_art_tut.gen_2_2 :as g]
            [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
   (g/setup))

(q/defsketch generative_art_tut
  :title "Generative Art tutorials."
  :size [500 300]
  :setup setup
  :draw g/draw-my-art)

(defn -main [& args])
