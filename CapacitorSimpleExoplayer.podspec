require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorSimpleExoplayer'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
<<<<<<< HEAD
  s.ios.deployment_target  = '12.0'
=======
  s.ios.deployment_target  = '11.0'
>>>>>>> 1a1eda2bacf6623cee60ffd2185b2c48bbd9b56c
  s.dependency 'Capacitor'
  s.swift_version = '5.1'
end
