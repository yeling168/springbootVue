package com.example.springboot01.service;

import com.example.springboot01.domain.Ebook;
import com.example.springboot01.domain.EbookExample;
import com.example.springboot01.mapper.EbookMapper;
import com.example.springboot01.req.EbookQueryReq;
import com.example.springboot01.req.EbookSaveReq;
import com.example.springboot01.resp.EbookQueryResp;
import com.example.springboot01.resp.PageResp;
import com.example.springboot01.util.CopyUtil;
import com.example.springboot01.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
////            EbookResp ebookResp = new EbookResp();
////            BeanUtils.copyProperties(ebook, ebookResp);
        //  对象复制
//            EbookResp ebookResp = CopyUtil(ebook, EbookResp.class);
//            respList.add(ebookResp);
//        }

        // 列表复制
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if(ObjectUtils.isEmpty(req.getId())){
            // 新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else{
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    /**
     * 删除
     */
     public void delete(Long id){
         ebookMapper.deleteByPrimaryKey(id);
     }
}
