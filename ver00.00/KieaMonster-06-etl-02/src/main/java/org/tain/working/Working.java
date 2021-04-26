package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.basket.BasketWorking;
import org.tain.working.board.BoardWorking;
import org.tain.working.campPage.CampPageWorking;
import org.tain.working.campaign.CampaignWorking;
import org.tain.working.tests.TestsWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) job01();  // for test
		if (Flag.flag) job02();  // for board
		if (Flag.flag) job03();  // for campPage
		if (Flag.flag) job04();  // for campaign
		if (Flag.flag) job05();  // for curl -X GET http://alpha-recsy.11st.co.kr/basket/suggest/gt/2245614696
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TestsWorking testsWorking;
	
	private void job01() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		//if (Flag.flag) this.testsWorking.jobTest01();
		//if (Flag.flag) this.testsWorking.jobTest02();
		
		if (!Flag.flag) this.testsWorking.jobTestGetPage();
		if (!Flag.flag) this.testsWorking.jobTestPost();
		if (!Flag.flag) this.testsWorking.jobTestGetSingle();
		if (!Flag.flag) this.testsWorking.jobTestPut();
		if (!Flag.flag) this.testsWorking.jobTestDelete();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private BoardWorking boardWorking;
	
	private void job02() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.boardWorking.selectAllAndSend();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private CampPageWorking campPageWorking;
	
	private void job03() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.campPageWorking.selectAllAndSend();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private CampaignWorking campaignWorking;
	
	private void job04() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.campaignWorking.selectAllAndSend();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private BasketWorking basketWorking;
	
	private void job05() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.basketWorking.selectSuggest();
	}
}
