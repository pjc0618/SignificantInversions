import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
	static class Result {
		public long count;
		public List<Integer> C;

		public Result(long count, List<Integer> C) {
			this.count = count;
			this.C = C;

		}
	}
	static Result mergeCount(List<Integer> A, List<Integer> B) {
		int count = 0; int i = 0; int j = 0;		
		List<Integer> C = new ArrayList<Integer>();
		while(i<A.size() && j<B.size()) {
			int a = A.get(i);
			int b = B.get(j);
			if (b < a) {
				for(int x = i; x<A.size();x++) {
					if(A.get(x)>1+B.get(j)) {
						count +=A.size()-x;
						break;
					}
				}
				C.add(b);
				j++;
			}else {i++;C.add(a);}//Bl-=1;
		}
		for(int k =i;k<A.size(); k++) C.add(A.get(k));
		for(int k =j;k<B.size(); k++) C.add(B.get(k));//adds remaining elements to C
		Result r = new Result(count, C);
		return r;
	}
	static Result sortCount(List<Integer> inputs) {
		int n = inputs.size();
		if (n <= 1) return new Result(0,inputs);
		else {
			List<Integer> A = inputs.subList(0,n/2);
			List<Integer> B= inputs.subList(n/2,n);
			//System.out.println(B);
			Result rA = sortCount(A); 
			//System.out.println("A is "+A);
			//System.out.println("B is "+B);
			Result rB = sortCount(B);
			//System.out.println("rA.C is "+rA.C);
			Result r = mergeCount(rA.C,rB.C);
			//System.out.println(rA.count+" "+rB.count+" "+r.count);
			return new Result(r.count+rA.count+rB.count,r.C);
		}
	}

	public static void main(String args[]) {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(in.readLine());
			String[] inputs = in.readLine().split("\\s+");
			List<Integer> ins = new ArrayList<Integer>();
			for(int i =0; i<n;i++)ins.add(Integer.parseInt(inputs[i]));
			Result result =sortCount(ins);
			System.out.println(result.count);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}