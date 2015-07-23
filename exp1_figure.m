figure;
M = load('D:\Work\Workspace\Scala\GetTopNProfit\exp1-bweight=0.200-pweight=0.200.txt');
x1 = M(:,1);
y1 = M(:,2);
M = load('D:\Work\Workspace\Scala\GetTopNProfit\exp1-bweight=0.100-pweight=0.100.txt');
x2 = M(:,1);
y2 = M(:,2);
plot(x1,y1,'r',x2,y2,'b');
grid on;
title('quote price = 5')

xlabel('flow weight');
ylabel('cost');
legend({'$bandwidth weigth = 0.2$,$privacy weight = 0.2$','$bandwidth weight = 0.1$,$privacy weight = 0.1$'},'interpreter','laTex')

