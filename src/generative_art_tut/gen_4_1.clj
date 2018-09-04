(ns generative_art_tut.gen_4_1
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; size(500, 300);
; background(255);
; strokeWeight(5);
; smooth();
; 
; float radius = 100;
; int centx = 250;
; int centy = 150;
; 
; stroke(0, 30);
; noFill();
; ellipse(centx, centy, radius * 2, radius * 2);
; 
; stroke(20,50, 70);
; float x, y;
; float lastx = -999;
; float lasty = -999;
; for (float ang = 0;ang<=360;ang+=5) {
;   float rad = radians(ang);
;   x = centx + (radius * cos(rad));
;   y = centy + (radius * sin(rad));
;   point(x,y);
; }
;;;; ここまで ;;;;;

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  (let [radius 100
        cent-x 250
        cent-y 150
        rads (map q/radians (range 0 360 5))
        xs (map #(+ cent-x (* radius (q/cos %))) rads)
        ys (map #(+ cent-y (* radius (q/sin %))) rads)
        ]
    (q/stroke 0 30)
    (q/no-fill)
    (q/ellipse cent-x cent-y (* radius 2) (* radius 2))
    (q/stroke 20 50 70)
    (dorun (map q/point xs ys)))
  (q/save-frame "gen.4.1.jpg"))

(q/defsketch gen_4_1
  :title "Dotted Circle"
  :setup setup
  :size [500 300]
  )

