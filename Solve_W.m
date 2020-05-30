修改四次
function [ W ] = Solve_W( W_t,v_t,v_opt,K,N,sigma,H_k,G_n,M,P )
%UNTITLED6 此处显示有关此函数的摘要
%   此处显示详细说明
%给定v求W
cvx_begin 
variable W(M,K) complex
variable s
expressions b(1,K) q(K,N) b_k(1,K) q_t(K,N)
maximize s
subject to
for k=1:K
    for n=1:N
        b(k)=0;
        q(k,n) = 0;
        b_t(k)= 0;
        q_t(k,n) = 0;
        for i = 1:K
            if i ~= k
                b(k) = b(k) + square_abs(v_opt'*H_k(:,:,k)*W(:,i)); 
                q(k,n) = q(k,n) + real(W_t(:,i)'*G_n(:,:,n)'*v_t*(v_opt'*2*G_n(:,:,n)*W(:,i)-v_t'*G_n(:,:,n)*W_t(:,i))); 
                b_t(k) = b_t(k) + square_abs(v_t'*H_k(:,:,k)*W_t(:,i)); 
                q_t(k,n) = q_t(k,n) + square_abs(v_t'*G_n(:,:,n)*W_t(:,i));
            else
                b(k) =b(k)+ 0;
                q(k,n) = q(k,n) + 0;
                b_t(k) =b_t(k)+ 0;
                q_t(k,n) = q_t(k,n) + 0;
            end
        end
            b(k)=b(k)+sigma;
            q(k,n) = q(k,n) + sigma;
            b_t(k)=b_t(k)+sigma;
            q_t(k,n) = q_t(k,n) + sigma;
            
            w_k = W_t(:,k);
            RB(k) = log(1+square_abs(v_t'*H_k(:,:,k)*w_k)/b_t(k));
            RE(k,n) = log(1+square_abs(v_t'*G_n(:,:,n)*w_k)/q_t(k,n));
            temp1 = 2*(real(w_k'*H_k(:,:,k)'*v_t*(v_opt'*H_k(:,:,k)*W(:,k))))/b_t(k);
            temp2 = square_abs(v_t'*H_k(:,:,k)*w_k)/(b_t(k)*(b_t(k)+square_abs(v_t'*H_k(:,:,k)*w_k)))*square_abs(v_opt'*H_k(:,:,k)*W(:,k))+b(k);
            temp3 = square_abs(v_t'*H_k(:,:,k)*w_k)/b_t(k);
            temp4 = (1/(1+(square_abs(v_t'*G_n(:,:,n)*w_k)/q_t(k,n))));
            temp5 = quad_over_lin(v_opt'*G_n(:,:,n)*W(:, k),q(k,n)) - square_abs(v_t'*G_n(:,:,n)*w_k)/q_t(k,n);
            f_B(k) = RB(k) + temp1 - temp2 - temp3;
            f_E(k,n) = RE(k,n) + temp4 * temp5;
            s<=f_B(k)-f_E(k,n);
    end   
end
sum(square_pos((norm(W(:),2))))<= P ;
cvx_end
end

