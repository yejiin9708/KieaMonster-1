package org.tain.controller.mon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.mybatis.mappers.CdItmMapper;
import org.tain.mybatis.mappers.CmdMapper;
import org.tain.mybatis.mappers.SvrMapper;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CmdController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private CmdMapper cmdMapper;
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cmdList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cmdMapper.selectAll(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
		}
		
		return "web/cmd/cmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private SvrMapper svrMapper;
	
	@Autowired
	private CdItmMapper cdItmMapper;
	
	@RequestMapping(value = {"/cmd/cmdForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> id: {}",id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		// test sample data
		if (Boolean.TRUE) {
			/*
			if (Boolean.TRUE) {
				String[] lstSvrCode = new String[] { "TEST01", "TEST02", "TEST03", "TEST04", "TEST05", "TEST06", "TEST07" };
				log.info("KANG-20200730 >>>>> lstSvrCode: {}", Arrays.deepToString(lstSvrCode));
				model.addAttribute("lstSvrCode", lstSvrCode);
			}
			
			if (Boolean.TRUE) {
				//String[] lstCmdLoop = new String[] { "keep(noLoop)", "loopSec(5sec)", "loopSec(10sec)", "loopSec(30sec)", "loopSec(60sec)" };
				Map<String,String> mapCmdLoop = new LinkedHashMap<>();
				mapCmdLoop.put("0",  "keep(no Loop)");
				mapCmdLoop.put("5",  "loopSec(5 sec)");
				mapCmdLoop.put("10", "loopSec(10 sec)");
				mapCmdLoop.put("30", "loopSec(30 sec)");
				mapCmdLoop.put("60", "loopSec(60 sec)");
				log.info("KANG-20200730 >>>>> mapCmdLoop: {}", mapCmdLoop);
				model.addAttribute("mapPeriod", mapCmdLoop);
			}
			
			if (Boolean.TRUE) {
				String[] lstPrtDir = new String[] { "prepend", "override", "append" };
				log.info("KANG-20200730 >>>>> lstPrtDir: {}", Arrays.deepToString(lstPrtDir));
				model.addAttribute("lstPattern", lstPrtDir);
			}
			*/
		}
		
		// real data
		if (Boolean.TRUE) {
			if (Boolean.TRUE) {
				List<Map<String,Object>> lstMapSvr = this.svrMapper.selectAll(null);
				log.info("KANG-20200730 >>>>> lstMapSvr: {}", lstMapSvr);
				model.addAttribute("lstMapSvr", lstMapSvr);
			}
			
			if (Boolean.TRUE) {
				Map<String,Object> mapIn = new HashMap<>();
				mapIn.put("code", "PERIOD");
				List<Map<String,Object>> lstMapPrd = this.cdItmMapper.selectAllByMst(mapIn);
				log.info("KANG-20200730 >>>>> lstMapPrd: {}", lstMapPrd);
				model.addAttribute("lstMapPrd", lstMapPrd);
			}
			
			if (Boolean.TRUE) {
				Map<String,Object> mapIn = new HashMap<>();
				mapIn.put("code", "PRN_PATTERN");
				List<Map<String,Object>> lstMapPttn = this.cdItmMapper.selectAllByMst(mapIn);
				log.info("KANG-20200730 >>>>> lstMapPttn: {}", lstMapPttn);
				model.addAttribute("lstMapPttn", lstMapPttn);
			}
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOne(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
		}
		
		return "web/cmd/cmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cmdView"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String view(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> id: {}",id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOne(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
		}
		
		return "web/cmd/cmdView";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdList/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String listByGrp(@PathVariable(value = "code") String code, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}",code);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cmdMapper.selectAllByGrp(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdForm/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String formByGrp(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneByGrp(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdView/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String viewByGrp(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneByGrp(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdView";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdList/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String listBySvr(@PathVariable(value = "code") String code, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}",code);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cmdMapper.selectAllBySvr(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdForm/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String formBySvr(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneBySvr(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdView/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String viewBySvr(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneBySvr(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdView";
	}
	
}
