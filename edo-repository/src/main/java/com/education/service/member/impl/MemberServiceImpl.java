package com.education.service.member.impl;

import com.education.entity.Member;
import com.education.repository.member.MemberRepository;
import com.education.service.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Сервис-слой для связи контроллера и репозитория сущности Member
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * Метод принимает сущность Member и сохраняет её в БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    /**
     * Метод принимает индекс и ищет сущность Member с таким индексом в БД.
     * Возвращает найденную сущность.
     */
    @Transactional(readOnly = true)
    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    /**
     * Метод возвращает все сущности Member, которые хранятся в БД
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * Метод принимает индексы и ищет сущности Member с такими индексами в БД.
     * Возвращает найденные сущности.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Member> findAllById(Iterable<Long> ids) {
        return memberRepository.findAllById(ids);
    }

    /**
     * Метод принимает индекс и удаляет сущность Member с таким индексом из БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
