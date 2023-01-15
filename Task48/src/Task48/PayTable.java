package Task48;

import java.text.NumberFormat;
import java.util.Scanner;

public class PayTable extends Salary implements Calculate, Output{
	
	public PayTable(String name, int pay, int family, int overtime) {
		super(name, pay, family, overtime);
	}
	
	public PayTable() {
	}

	public void title() {
		System.out.println("\t\t\t\t\t\t\t**** �޿����� ****");
		for(int i=0;i<140;i++) {
			System.out.print("-");}
		System.out.println("\n�̸�\t�⺻��\t\t������\t��������\t\t�ð��ܱٹ�"
				+"\t\t�ð��ܼ���\t\t����\t\t�μ�Ƽ��(%)\t������\t\t�Ǽ��ɾ�");
		for(int i=0;i<140;i++) {
			System.out.print("-");}
	}
	
	public void footer() {
	    System.out.println("\n�ο���: "+getCount()+"��");
	}
	
	@Override
	public void out() {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		
		System.out.print("\n"+getName()+"\t"+formatter.format(getPay())+"��\t"+getFamily()+"\t");
		System.out.print(formatter.format(getFamily_p())+"��\t\t"+getOvertime()+"\t\t"+formatter.format(getOvertime_p())+"��\t\t"
		+formatter.format(getTax())+"��\t\t"+getIncentive()*100f+"\t\t"+formatter.format(getIncen_p())+"��\t"
		+formatter.format(getTotal_pay())+"��");
	}

	@Override
	public void calc() {
		if(getFamily()<3) {
			setFamily_p(getFamily()*20000);
		}else {
			setFamily_p(60000);
		}
		//setFamily_p( (getFamily()>=3)?60000:getFamily()*20000 ); ### ?�����U�� ���� ���� ��, �ݷ�(:)�������� ������ ���� ��.
		setOvertime_p(getOvertime()*5000);
		setTax((int)(getPay()*0.1));
		setIncen_p((int)(getIncentive()*getPay()));
		setTotal_pay((int)(getPay()+getFamily_p()+getIncen_p()+getOvertime_p()-getTax()));
	}
	
	public static void main(String[] args) {
		
		PayTable tt=new PayTable();
		Scanner scan=new Scanner(System.in);
		String con;
		
		PayTable []pt = new PayTable[3];
	    pt[0] = new PayTable("�Ҽ���",750000,2,3);
	    pt[1] = new PayTable("���ָ�",800000,1,4);
	    pt[2] = new PayTable("���ȸ�",650000,6,2);
	    
	    float a;
	    
	    while(true) {
		    tt.title();
		    for(int i=0;i<pt.length;i++) {
			    pt[i].calc();
			    pt[i].out();
		    }
		    setCount(pt.length);
		    tt.footer();
		    
		    System.out.println("\n����Ϸ��� �ƹ� Ű�� �Է��ϼ���...");
		    con=scan.next();
		    
		    do {
		    	System.out.print("\n�μ�Ƽ��(%)�� �Է��ϼ���: ");
			    try {
			    a=Float.parseFloat(scan.next());
			    setIncentive(a*0.01f);
			    }catch(Exception e){
			    	a=-1;
			    	System.out.println("�߸��� �Է�. ������ �Է��ϼ���.");
			    }
		    }while(a<0);
		    
	    }

	}

}
