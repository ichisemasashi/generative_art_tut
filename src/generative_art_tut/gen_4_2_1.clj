(ns generative_art_tut.gen_4_2_1
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; void setup() {
;   size(500, 300);
;   background(255);
;   strokeWeight(5);
;   smooth();
;   
;   float radius = 100;
;   int centx = 250;
;   int centy = 150;
;   stroke(0, 30);
;   noFill();
;   ellipse(centx, centy, radius*2, radius*2);
;   
;   stroke(20, 50, 70);
;   strokeWeight(1);
;   float x1, y1, x2, y2;
;   float lastx = -999;
;   float lasty = -999;
; 
;   for (float ang = 0; ang <= 120; ang += 1) {
;     float rad = radians(ang);
;     x1 = centx + (radius * cos(rad));
;     y1 = centy + (radius * sin(rad));
;     x2 = centx + (radius * cos(rad + PI));
;     y2 = centy + (radius * sin(rad + PI));
;     line(x1, y1, x2, y2);
;   }
; }
;;;; ここまで ;;;;;
(defn- bk_gray [] (q/background 255))
(defn- circle [x y r]
  (q/ellipse x y r r))
(defn- half [x]
  (/ x 2))

(defn setup []
  (bk_gray)
  (q/smooth)

  (let [d 120
        p 180
        radius 100
        cent-x (half (q/width))
        cent-y (half (q/height))
        angles (map q/radians (range 0 d 1))
        angles2 (map q/radians (range p (+ d p) 1))
        x1 (map #(+ cent-x (* radius (q/cos %))) angles)
        y1 (map #(+ cent-y (* radius (q/sin %))) angles)
        x2 (map #(+ cent-x (* radius (q/cos %))) angles2)
        y2 (map #(+ cent-y (* radius (q/sin %))) angles2)
        ]
    (q/stroke 0 30)
    (q/no-fill)
    (q/stroke-weight 5)
    (circle cent-x cent-y (* 2 radius))
    (q/stroke 20 50 70)
    (q/stroke-weight 1)
    (dorun (map q/line x1 y1 x2 y2)))
  (q/save-frame "gen.4.2.1.jpg"))
(q/defsketch gen_4_2_1
  :title "circle 1 before Wave Clock"
  :setup setup
  :size [500 300]
  )

