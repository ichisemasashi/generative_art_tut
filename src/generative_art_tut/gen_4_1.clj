(ns generative_art_tut.gen_4_1
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
size(500, 300);
background(255);
strokeWeight(5);
smooth();

float radius = 100;
int centx = 250;
int centy = 150;

stroke(0, 30);
noFill();
ellipse(centx, centy, radius * 2, radius * 2);

stroke(20,50, 70);
float x, y;
float lastx = -999;
float lasty = -999;
for (float ang = 0;ang<=360;ang+=5) {
  float rad = radians(ang);
  x = centx + (radius * cos(rad));
  y = centy + (radius * sin(rad));
  point(x,y);
}
;;;; ここまで ;;;;;
(defn- my-zip [xs ys]
  (map #(list %1 %2 %3 %4) xs ys (rest xs) (rest ys)))
(defn- my-rand [x]
  (- 1 (q/pow (rand 1) 5)))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step 3
        border-x 20
        xs (range border-x (- (q/width) border-x) step)
        ys (map #(+ 20 (* 60 (my-rand %))) (range))
        line-args (my-zip xs ys)]
        (dorun (map #(apply q/line %) line-args)))
  (q/save-frame "gen.4.1.jpg"))

(q/defsketch gen_4_1
  :title "sine^3 and noise curve"
  :setup setup
  :size [500 100]
  )

