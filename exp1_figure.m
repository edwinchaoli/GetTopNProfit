figure;
N = load('D:\Work\Workspace\Scala\GetTopNProfit\exp1.txt');


M = sortrows(N,1);
x = M(:,1);
y = M(:,2);

plot(x,y,'^r');

grid on;
title('quote price = 100')

xlabel('{\it{\theta}}');
ylabel('{\it{profit}}');
!legend({'$bandwidth weigth = 0.2$,$privacy weight = 0.2$','$bandwidth weight = 0.1$,$privacy weight = 0.1$'},'interpreter','laTex')

