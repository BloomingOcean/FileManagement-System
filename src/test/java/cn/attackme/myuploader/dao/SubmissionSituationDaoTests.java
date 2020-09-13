package cn.attackme.myuploader.dao;

import cn.attackme.myuploader.model.SubmissionSituation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubmissionSituationDaoTests {
    @Autowired
    private SubmissionSituationDao submissionSituationDao;

    private SubmissionSituation testSubmissionSituation;


    /**
     * 生成File实例
     */
    @Before
    public void generateFile() {
        testSubmissionSituation = new SubmissionSituation();
        testSubmissionSituation.setFileId(666);
        testSubmissionSituation.setClassId(888);
        testSubmissionSituation.setSituation("测试提交");
        testSubmissionSituation.setFilePath(UUID.randomUUID().toString());
        testSubmissionSituation.setUploadTime(new Date());
    }

    /**
     * File保存成功测试
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveSuccess(){
        assertEquals(1, submissionSituationDao.save(testSubmissionSituation));
    }

    /**
     * FileId、ClassId、FilePath为null保存抛异常测试
     */
    @Test(expected = Exception.class)
    public void testSaveExceptionByNameIsNull() {
        testSubmissionSituation.setFileId(null);
        testSubmissionSituation.setClassId(null);
        testSubmissionSituation.setFilePath(null);
        submissionSituationDao.save(testSubmissionSituation);
    }

    /**
     * 更新成功测试
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateSuccess() {
        submissionSituationDao.save(testSubmissionSituation);
        testSubmissionSituation.setFilePath(UUID.randomUUID().toString());
        assertEquals(1, submissionSituationDao.update(testSubmissionSituation));
    }

    /**
     * FileId、ClassId为null更新不抛异常测试
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateNoExceptionByMd5IsNull() {
        submissionSituationDao.save(testSubmissionSituation);
        testSubmissionSituation.setFileId(null);
        testSubmissionSituation.setClassId(null);
        submissionSituationDao.update(testSubmissionSituation);
    }

    /**
     * 根据id获取File成功测试
     */
    @Test
    @Transactional
    @Rollback
    public void testGetByIdSuccess() {
        submissionSituationDao.save(testSubmissionSituation);
        assertNotNull(submissionSituationDao.getById(testSubmissionSituation.getSubId()));
    }

    /**
     * 根据不存在的id获取为null
     */
    @Test
    public void testGetByNotExitsIdReturnNull() {
        assertNull(submissionSituationDao.getById(new Integer(0)));
    }

    /**
     * 根据id删除File成功
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteByIdSuccess() {
        submissionSituationDao.save(testSubmissionSituation);
        assertEquals(1, submissionSituationDao.deleteById(testSubmissionSituation.getSubId()));
    }

    /**
     * 根据不存在的id删除0条数据
     */
    @Test
    public void testDeleteByNotExitsReturn0() {
        assertEquals(0, submissionSituationDao.deleteById(new Integer(0)));
    }

    /**
     * 根据多列查询成功
     */
    @Test
    @Transactional
    @Rollback
    public void testGetByFileSuccess() {
        submissionSituationDao.save(testSubmissionSituation);
        assertNotNull(submissionSituationDao.getByFile(testSubmissionSituation));
    }

    /**
     * 根据某一列获取不存在的file,返回null
     */
    @Test
    public void testGetByNotExistFileReturnNull() {
        SubmissionSituation submissionSituation = new SubmissionSituation();
        submissionSituation.setSubId(new Integer(0));
        assertNull(submissionSituationDao.getByFile(submissionSituation));
    }

    /**
     * File传null, where条件失效, 返回多行, 抛异常
     */
    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testGetByNullThrowException() {
        submissionSituationDao.save(testSubmissionSituation);
        submissionSituationDao.save(testSubmissionSituation);
        submissionSituationDao.getByFile(null);
    }

    /**
     * 根据不唯一列获取到多条数据, 抛出异常
     */
    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testGetByNotUniqueColumnThrowException() {
        submissionSituationDao.save(testSubmissionSituation);
        submissionSituationDao.save(testSubmissionSituation);
        SubmissionSituation submissionSituation = new SubmissionSituation();
        submissionSituation.setFileId(testSubmissionSituation.getFileId());
        submissionSituation.setClassId(testSubmissionSituation.getClassId());
        submissionSituationDao.getByFile(submissionSituation);
    }
}
